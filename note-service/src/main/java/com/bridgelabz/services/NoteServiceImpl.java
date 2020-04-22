package com.bridgelabz.services;

import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bridgelabz.UserServiceProxy;
import com.bridgelabz.dto.NoteDto;
import com.bridgelabz.entity.Note;
import com.bridgelabz.entity.User;
import com.bridgelabz.exceptions.UserNotFoundException;
import com.bridgelabz.repository.NoteRepo;
import com.bridgelabz.utility.JWTGenerator;

@Service
public class NoteServiceImpl implements INoteService{
	
	@Autowired
	private Note noteInfo; 
	
	@Autowired
	private NoteRepo noteRepo;
	
	@Autowired
	private JWTGenerator jwtGenerate;
	
	@Autowired
	private ModelMapper model;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private UserServiceProxy proxy;
	/**
	 * Method is used to create a Note
	 */
	@Transactional
	@Override
	public Note createNote(NoteDto noteDtoInfo, String token)
	{
		System.out.println("2 Inside Note service");
		
//		Long userId = (Long) jwtGenerate.parseToken(token);
//		userInfo = userRepo.findUserById(userId);
		
//		User userInfo=restTemplate.getForObject("http://localhost:8765/user-service/user/getuser"+"/"+token, User.class);
		
		User userInfo=proxy.getUserById(token);
		System.out.println("5 User in NoteService "+userInfo);
		if (userInfo != null)
		{
			System.out.println("About to create note");
			noteInfo = model.map(noteDtoInfo, Note.class);
			noteInfo.setCreatedAt(LocalDateTime.now());
			noteInfo.setArchieved(false);
			noteInfo.setPinned(false);
			noteInfo.setTrashed(false);
			noteInfo.setColor("white");
			Note note = noteRepo.save(noteInfo);
			
			System.out.println("After note repo");
			return noteInfo;
		}
		else
			return null;
	}
}
