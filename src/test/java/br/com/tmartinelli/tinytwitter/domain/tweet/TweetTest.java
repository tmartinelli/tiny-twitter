package br.com.tmartinelli.tinytwitter.domain.tweet;

import static org.junit.Assert.assertEquals;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;

import br.com.tmartinelli.tinytwitter.domain.user.User;


public class TweetTest {

	private Validator validator;

	@Before
	public void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	    validator = factory.getValidator();
	}
	
	@Test
	public void thereShouldBeErrorWhenContentBeNull() {
		Tweet tweet = new Tweet(new User(), null);
		Set<ConstraintViolation<Tweet>> constraintViolations = validator.validateProperty(tweet, "content");

		assertEquals(1, constraintViolations.size());
		assertEquals("{tweet.content.empty}", constraintViolations.iterator().next().getMessageTemplate());
	}
	
	@Test
	public void thereShouldBeErrorWhenContentBeBlank() {
		Tweet tweet = new Tweet(new User(), "");
		Set<ConstraintViolation<Tweet>> constraintViolations = validator.validateProperty(tweet, "content");
		
		assertEquals(1, constraintViolations.size());
		assertEquals("{tweet.content.length}", constraintViolations.iterator().next().getMessageTemplate());
	}

	@Test
	public void thereShouldBeErrorWhenContentBeLarger() {
		Tweet tweet = new Tweet(new User(), StringUtils.leftPad("a", 141));
		Set<ConstraintViolation<Tweet>> constraintViolations = validator.validateProperty(tweet, "content");
		
		assertEquals(1, constraintViolations.size());
		assertEquals("{tweet.content.length}", constraintViolations.iterator().next().getMessageTemplate());
	}
}
