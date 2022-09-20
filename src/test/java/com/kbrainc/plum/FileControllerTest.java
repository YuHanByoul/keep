package com.kbrainc.plum;

import org.junit.Before;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.kbrainc.plum.cmm.file.controller.FileController;

public class FileControllerTest {

	private MockMvc mockMvc;
	
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.standaloneSetup( new FileController() ).build();
	}
}
