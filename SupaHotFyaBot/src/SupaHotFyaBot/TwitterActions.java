// Colin 

// This class is for actions taken, such as posting tweets, sending direct messages, etc. 


package SupaHotFyaBot;

import java.util.List;
import java.util.Scanner;

import twitter4j.DirectMessage;
import twitter4j.Status;
import twitter4j.Twitter;


public class TwitterActions {

	// method to post a tweet
	public static void postTweet() throws Exception{
		
		Twitter twitter = TwitterAuth.getTwitterObject();
		
		System.out.println("Please enter a message to tweet: ");
		Scanner keyboard = new Scanner(System.in);	// closing this causes an error in the sendDirectMessage method for some reason
		
		String message = keyboard.nextLine();
	
		
	    Status status = twitter.updateStatus(message);
	    System.out.println("Successfully updated the status to <" + status.getText() + ">.");
		
	}
	
	// method to display your timeline
	private static void getTimeline() throws Exception{
		Twitter twitter = TwitterAuth.getTwitterObject();
		
		List<Status> statuses = twitter.getHomeTimeline();
	    System.out.println("Showing home timeline: /n/n");
	    
	    for (Status status : statuses) {
	        System.out.println(status.getUser().getName() + ":" +
	                           status.getText());
	    }

	}
	
	// method to send a direct message to someone 
	private static void sendDirectMessage() throws Exception{
	 
		Twitter twitter = TwitterAuth.getTwitterObject();
		
	    System.out.println("Who do you want to send a direct message to?: ");
	    Scanner keyboard = new Scanner(System.in);
	    String recipientId = keyboard.nextLine();
	   
	    
	    System.out.println("Please enter your direct message: ");
	    String direct_message = keyboard.nextLine();
	    
	    keyboard.close();
	    
	    DirectMessage message = twitter.sendDirectMessage(recipientId, direct_message);
	    System.out.println("Sent: <" + message.getText() + "> to <@" + message.getRecipientScreenName() + ">");
	}

	
	
	public static void main(String[] args) throws Exception{
		new TwitterAuth();
		
		TwitterAuth.authenticate();
		postTweet();
		sendDirectMessage();
		getTimeline();

	
	}
}
