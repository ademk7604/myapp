package com.project.myapp.controllers;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.project.myapp.entities.Comment;
import com.project.myapp.services.CommentService;

@SpringBootTest
@AutoConfigureMockMvc
public class CommentControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CommentService commentService;

	@Test
	public void testGetAllComments() throws Exception {
		List<Comment> comments = new ArrayList<>();
		Comment comment1 = new Comment();
		comment1.setId(1L);
		Comment comment2 = new Comment();
		comment2.setId(2L);
		comments.add(comment1);
		comments.add(comment2);

		when(commentService.getAllCommentsWithParam(Optional.empty(), Optional.empty())).thenReturn(comments);

		mockMvc.perform(get("/comments")).andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(2)))
				.andExpect(jsonPath("$[0].id").value(1)).andExpect(jsonPath("$[1].id").value(2));
	}

}
