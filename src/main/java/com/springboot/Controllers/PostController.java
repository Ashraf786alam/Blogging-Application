package com.springboot.Controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.Config.AppConstants;
import com.springboot.Entity.Post;
import com.springboot.PayLoads.ApiResponse;
import com.springboot.PayLoads.ImageResponse;
import com.springboot.PayLoads.PostDto;
import com.springboot.PayLoads.PostResponse;
import com.springboot.Service.FileUploadService;
import com.springboot.Service.PostService;

@RestController
@RequestMapping("/api")
public class PostController {
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private FileUploadService fileService;
	
	@Value("${project.image}")
	private String path;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@PostMapping("/user/{userId}/category/{categoryId}/post")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postdto,@PathVariable int userId,@PathVariable int categoryId) {
		
	   PostDto postDto=this.postService.createPost(postdto, userId, categoryId);
	   return new ResponseEntity<PostDto>(postDto,HttpStatus.CREATED);
	
	}
	
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable int userId){
		
		List<PostDto> user_posts=this.postService.getAllPostByUser(userId);
		return new ResponseEntity<List<PostDto>>(user_posts,HttpStatus.OK);
		
	}
	
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable int categoryId){
		List<PostDto> category_posts=this.postService.getAllPostByCategory(categoryId);
		return new ResponseEntity<List<PostDto>>(category_posts,HttpStatus.OK);
	}
	
	@GetMapping("/posts")
	public ResponseEntity<PostResponse> getAllPost(@RequestParam(value="pageNumber",defaultValue=AppConstants.PAGE_NUMBER ,required=false) int pageNumber,
			@RequestParam(value="pageSize",defaultValue=AppConstants.PAGE_SIZE ,required=false) int pageSize,
			@RequestParam(value="sortBy",defaultValue=AppConstants.SORT_BY,required=false) String sortBy,
			@RequestParam(value="sortDir", defaultValue=AppConstants.SORT_DIR,required=false) String sortDir){
		
		PostResponse postresponse=this.postService.getAllPost(pageNumber,pageSize,sortBy,sortDir);
		return new ResponseEntity<PostResponse>(postresponse,HttpStatus.OK);
		
	}
	
	@GetMapping("/posts/{postId}")
	public ResponseEntity<PostDto> getPostById(@PathVariable int postId){
		
		PostDto postdto=this.postService.getPostById(postId);
		return new ResponseEntity<PostDto>(postdto,HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/post/{postId}")
	public ResponseEntity<ApiResponse> DeletePost(@PathVariable int postId){
		
		ApiResponse apiresponse=new ApiResponse("Post Deleted Successfully with Id "+postId,true);
		this.postService.deletePost(postId);
		return new ResponseEntity<ApiResponse>(apiresponse,HttpStatus.OK); 
		
	}
	
	@PutMapping("/post/update/{postId}")
	public ResponseEntity<PostDto> UpdatePost(@RequestBody PostDto postdto,@PathVariable int postId){
		
		PostDto updated_post=this.postService.UpdatePost(postdto, postId);
		return new ResponseEntity<PostDto>(updated_post,HttpStatus.OK);
	}
	
	@GetMapping("/post/search/{title}")
	public ResponseEntity<List<PostDto>> SearchPostByTile(@PathVariable String title){
		
		List<PostDto> postdtos=this.postService.searchPost(title);
		return new ResponseEntity<List<PostDto>>(postdtos,HttpStatus.OK);
		
	}
	
	@GetMapping("/post/search_title/{name}")
	public ResponseEntity<List<PostDto>> SearchByTitleStartingWith(@PathVariable String name){
		
		List<PostDto> postdtos=this.postService.searchByTitleStartingWith(name);
		return new ResponseEntity<List<PostDto>>(postdtos,HttpStatus.OK);
	}
	
	@GetMapping("/post/search/content/{content}")
	public ResponseEntity<List<PostDto>> SearchContentContaining(@PathVariable String content){
		
		List<PostDto> postdtos=this.postService.searchContentContaining(content);
		return new ResponseEntity<List<PostDto>>(postdtos,HttpStatus.OK);
	}
	
	
	@PostMapping("/post/image/upload/{postId}")
	public ResponseEntity<PostDto> UploadImage( @RequestParam("image") MultipartFile file,@PathVariable int postId) throws IOException{
		
		PostDto postdto=this.postService.getPostById(postId);
		String fileName=this.fileService.UploadImage(path, file);
		
		postdto.setImageName(fileName);
		PostDto Updatedpostdto=this.postService.UpdatePost(postdto, postId);
		return new ResponseEntity<PostDto>(Updatedpostdto,HttpStatus.OK);
		
	}
	
	@GetMapping("/post/image/{fileName}")
	public void DownloadFile(@PathVariable String fileName,HttpServletResponse response) throws IOException {
		
		  InputStream resource=this.fileService.ServeFile(path, fileName);
		  response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		  StreamUtils.copy(resource,response.getOutputStream());
		
		
	}

}
