package com.bridgelabz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.dto.NoteDto;
import com.bridgelabz.entity.Note;
import com.bridgelabz.response.Response;
import com.bridgelabz.services.INoteService;

@RestController
public class NoteController {
	@Autowired
	private INoteService noteService;
	/**
	 * API to create Notes
	 * @param noteDtoInfo
	 * @return
	 */
	@PostMapping ("notes/create/{token}")
	public ResponseEntity<Response> createNote(@RequestBody NoteDto noteDtoInfo,@PathVariable("token") String token)
	{
		System.out.println("1 inside create note controller");
		Note noteInfo=noteService.createNote(noteDtoInfo, token);
		if(noteInfo != null) {
			System.out.println("6 inside Note Created  ");
			return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Note is created successfully",  200));
		
		}else
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Note Creation failed", 400));
	}
	
}
