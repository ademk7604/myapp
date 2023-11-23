package com.project.myapp.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.myapp.entities.Comment;
import com.project.myapp.requests.CommentCreateRequest;
import com.project.myapp.requests.CommentUpdateRequest;
import com.project.myapp.services.CommentService;

@SpringBootTest
@AutoConfigureMockMvc
public class CommentControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CommentService commentService;

	/*
	@Test
	public void testGetAllComments() throws Exception {
	   
	    Comment comment1 = new Comment();
	    comment1.setId(1L);
	    Comment comment2 = new Comment();
	    comment2.setId(2L);

	    
	    CommentResponse commentResponse1 = new CommentResponse(comment1);
	    CommentResponse commentResponse2 = new CommentResponse(comment2);

	    
	    List<CommentResponse> comments = new ArrayList<>();
	    comments.add(commentResponse1);
	    comments.add(commentResponse2);

	    
	    when(commentService.getAllCommentsWithParam(Optional.empty(), Optional.empty())).thenReturn(comments);

	   
	    mockMvc.perform(get("/comments"))
	            .andExpect(status().isOk()) 
	            .andExpect(jsonPath("$", hasSize(2))) 
	            .andExpect(jsonPath("$[0].id").value(1)) 
	            .andExpect(jsonPath("$[1].id").value(2)); 
	}
	*/

	@Test
	public void testGetOneComment() throws Exception {
		long commentId = 1L;
		Comment comment = new Comment();
		comment.setId(commentId);
		when(commentService.getOneCommentById(commentId)).thenReturn(comment);

		mockMvc.perform(get("/comments/{commentId}", commentId)).andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(commentId));
	}

	@Test
	public void testCreateOneComment() throws Exception {
		CommentCreateRequest newComment = new CommentCreateRequest();
		newComment.setText("This is a new comment");

		Comment createdComment = new Comment();
		createdComment.setId(1L);
		createdComment.setText(newComment.getText());

		when(commentService.createOneComment(newComment)).thenReturn(createdComment);

		mockMvc.perform(post("/comments").contentType(MediaType.APPLICATION_JSON).content(asJsonString(newComment)))
				.andExpect(status().isOk()).andExpect(jsonPath("$.id").value(1))
				.andExpect(jsonPath("$.text").value(newComment.getText()));

	}

	@Test
	public void testUpdateOneComment() throws Exception {
		long commentId = 1L;
		CommentUpdateRequest request = new CommentUpdateRequest();
		request.setText("Updated comment text");

		Comment updatedComment = new Comment();
		updatedComment.setId(commentId);
		updatedComment.setText(request.getText());

		when(commentService.updateOneCommentById(eq(commentId), any(CommentUpdateRequest.class)))
				.thenReturn(updatedComment);

		mockMvc.perform(put("/comments/{commentId}", commentId).contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(request))).andExpect(status().isOk()).andExpect(jsonPath("$.id").value(commentId))
				.andExpect(jsonPath("$.text").value(request.getText()));
	}

	@Test
	public void testDeleteOneComment() throws Exception {
		long commentId = 1L;

		doNothing().when(commentService).deleteCommentById(commentId);

		mockMvc.perform(delete("/comments/{commentId}", commentId)).andExpect(status().isOk());

	}

	private String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}

}
