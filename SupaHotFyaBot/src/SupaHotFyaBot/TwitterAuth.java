// Colin 
// This class is for authenticating the Twitter object


package SupaHotFyaBot;

import java.io.BufferedReader;
import java.io.FileReader;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.TwitterException;
import java.io.IOException;


public class TwitterAuth {

	public static Twitter twitter = new TwitterFactory().getInstance();	// Twitter object to be used throughout the session
	final static String[] key_array = new String[4];	// array to hold the keys from the file 
	
	public static void authenticate() throws TwitterException, IOException{
		Twitter twitter = getTwitterObject();	// get the Twitter object to be used
		
		int i = 0;
		
		// read in the keys from the file
		// currently the array hold four keys, but we only use two
		BufferedReader br = new BufferedReader(new FileReader("/home/oscaricardo/Desktop/Keys.txt"));	
		String line;
		while ((line = br.readLine()) != null) {
		   key_array[i] = line;
		   i++;
		}
		
		br.close();
		
		// System.out.println(Arrays.toString(key_array));	// print the key array just to make sure it's working properly 
		
		twitter.setOAuthConsumer(key_array[0], key_array[1]);	
		
		String accessToken = getSavedAccessToken();
		String accessTokenSecret = getSavedAccessTokenSecret();
		
		AccessToken oathAccessToken = new AccessToken(accessToken, accessTokenSecret);
		
		twitter.setOAuthAccessToken(oathAccessToken);
	}
	
	// method to get the previously stored Access Token
	private static String getSavedAccessToken() throws IOException{
		// consider this as a method to get your previously saved Access Token
		
		String access_token = key_array[2];
		return access_token;
	}
	
	// method to get the previously stored Secret Access Token
	private static String getSavedAccessTokenSecret(){
		String access_token_secret = key_array[3];
		return access_token_secret;
	}
	
	public static Twitter getTwitterObject(){
		return twitter;
	}
	
	public static void main(String[] args) {
		System.out.println("horse");

	}

}
