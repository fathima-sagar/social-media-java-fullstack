package com.org.social.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.social.exceptions.ThrowException;
import com.org.social.model.Comment;
import com.org.social.model.Post;
import com.org.social.model.User;
import com.org.social.repository.CommentRepository;
import com.org.social.repository.PostRepository;
import com.org.social.repository.UserRepository;

@Service
public class CommentServiceImplementation implements CommentService {
	
	@Autowired
	private PostRepository postRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private CommentRepository commentRepo;

	@Override
	public Comment createComment(Comment comment, int postId, int userId) {
		Post post = postRepo.findById(postId).orElseThrow(()-> new ThrowException("post not found with Id"));
		User user = userRepo.findById(userId).orElseThrow(()-> new ThrowException("user not found with Id"));
		comment.setUser(user);
		comment.setContent(comment.getContent());
		Comment saveComment = commentRepo.save(comment);

		post.getComments().add(saveComment);
		postRepo.save(post);
		return saveComment;
	}

	@Override
	public Comment getById(int commentId) {
		Comment comment = commentRepo.findById(commentId).orElseThrow(()->new ThrowException("Comment Not FOund"));
		return comment;
	}

	@Override
	public Comment likeComment(int commentId, int userId) {
		// TODO Auto-generated method stub
		
		Comment comment = getById(commentId);
		User user = userRepo.findById(userId).orElseThrow(()-> new ThrowException("user not found with Id"));
		if(!comment.getLiked().contains(user)) {
			comment.getLiked().add(user);
		}else {
			comment.getLiked().remove(user);
		}
		
		return commentRepo.save(comment);
	}

}
