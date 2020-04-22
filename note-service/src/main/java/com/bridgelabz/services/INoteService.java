package com.bridgelabz.services;

import com.bridgelabz.dto.NoteDto;
import com.bridgelabz.entity.Note;

public interface INoteService {

	Note createNote(NoteDto noteDtoInfo, String token);
}
