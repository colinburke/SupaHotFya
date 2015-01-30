package SupaHotFyaBot;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.lang.*;

import twitter4j.DirectMessage;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.StatusUpdate;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

public class TwitterActions {

	
	public Twitter start() throws TwitterException, IOException{
		
		int i = 0;
		String[] key_array = new String[4];		// array to hold the keys from the file 
		
		BufferedReader br = new BufferedReader(new FileReader("/home/oscaricardo/Desktop/Keys.txt"));
		String line;
		while ((line = br.readLine()) != null) {
		   key_array[i] = line;
		   i++;
		}
		br.close();
		
		System.out.println(Arrays.toString(key_array));	// print the key array just to make sure it's alright 
	
		
		
		Twitter twitter = new TwitterFactory().getInstance();	// for future reference, this instance has all teh authorisation associated with it 
		
		twitter.setOAuthConsumer(key_array[0], key_array[1]);
		
	
		String accessToken = getSavedAccessToken();
		String accessTokenSecret = getSavedAccessTokenSecret();
		
		AccessToken oathAccessToken = new AccessToken(accessToken, accessTokenSecret);
		
		twitter.setOAuthAccessToken(oathAccessToken);
		/*
		// I'm reading your Timeline
		
		List<Status> statuses = twitter.getHomeTimeline();
	    for (Status status : statuses) {
	    	System.out.println("Sent by @" + status.getUser().getScreenName() + "-" + status.getUser().getName() + "/n" + status.getText() + "/n");
	    } 
	    
	    */
		
		return twitter;
		
	}
	
	
	private String getSavedAccessToken() throws IOException{
		// consider this as a method to get your previously saved Access Token
		// should change this to read input from a file
		
		
		
		String access_token = "2996704816-1RRBHj6kMJqTB7Cirn4UN9BqAmyFysGmW4dXLNo";
		return access_token;
	}
	
	private String getSavedAccessTokenSecret(){
		// consider this as a method to get your previously saved Secret Access Token
		// should change this to read input from a file
		String access_token_secret = "BhP8fZ8jbz3XXEPcSMdm0RGyxcRCl5oNxAj8vEJm74OrW";
		return access_token_secret;
	}
	
	
	private void postTweet(Twitter twitter) throws Exception{
		System.out.println("Please enter a message to tweet: ");
		Scanner keyboard = new Scanner(System.in);
		String message = keyboard.nextLine();
		
	    Status status = twitter.updateStatus(message);
	    System.out.println("Successfully updated the status to [" + status.getText() + "].");
		
	}
	
	private void getTimeline(Twitter twitter) throws Exception{
	    List<Status> statuses = twitter.getHomeTimeline();
	    System.out.println("Showing home timeline: /n/n");
	    
	    for (Status status : statuses) {
	        System.out.println(status.getUser().getName() + ":" +
	                           status.getText());
	    }

	}
	
	private void sendDirectMessage(Twitter twitter) throws Exception{
	 
	    System.out.println("Who do you want to send a direct message to?: ");
	    Scanner keyboard = new Scanner(System.in);
	    String recipientId = keyboard.nextLine();
	    
	    System.out.println("Please enter your direct message: ");
	    String direct_message = keyboard.nextLine();
	    
	    DirectMessage message = twitter.sendDirectMessage(recipientId, direct_message);
	    System.out.println("Sent: " + message.getText() + " to @" + message.getRecipientScreenName());
	}
	
	public static void main(String[] args) throws Exception{
		Twitter global_twitter = new TwitterActions().start();	// creates a global twitter instance, which is authenticated
		
		
		 new TwitterActions().postTweet(global_twitter);
		// new TwitterActions().getTimeline(global_twitter);
		// new TwitterActions().sendDirectMessage(global_twitter);
	}
}
