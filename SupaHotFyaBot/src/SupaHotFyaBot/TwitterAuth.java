// Colin 
// This class is for authorising the Twitter object


package SupaHotFyaBot;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

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

public class TwitterAuth {

	public static Twitter twitter = new TwitterFactory().getInstance();
	
	public static void authorise() throws TwitterException, IOException{
		Twitter twitter = getTwitterObject();
		
		int i = 0;
		String[] key_array = new String[4];		// array to hold the keys from the file 
		
		BufferedReader br = new BufferedReader(new FileReader("/home/oscaricardo/Desktop/Keys.txt"));	// read in the keys from the file (currently holds 4 but we only use 2)
		String line;
		while ((line = br.readLine()) != null) {
		   key_array[i] = line;
		   i++;
		}
		
		br.close();
		
		System.out.println(Arrays.toString(key_array));	// print the key array just to make sure it's alright 
		
		twitter.setOAuthConsumer(key_array[0], key_array[1]);
		
		String accessToken = getSavedAccessToken();
		String accessTokenSecret = getSavedAccessTokenSecret();
		
		AccessToken oathAccessToken = new AccessToken(accessToken, accessTokenSecret);
		
		twitter.setOAuthAccessToken(oathAccessToken);
	}
	
	private static String getSavedAccessToken() throws IOException{
		// consider this as a method to get your previously saved Access Token
		// should change this to read input from a file
		
		
		
		String access_token = "2996704816-1RRBHj6kMJqTB7Cirn4UN9BqAmyFysGmW4dXLNo";
		return access_token;
	}
	
	private static String getSavedAccessTokenSecret(){
		// consider this as a method to get your previously saved Secret Access Token
		// should change this to read input from a file
		String access_token_secret = "BhP8fZ8jbz3XXEPcSMdm0RGyxcRCl5oNxAj8vEJm74OrW";
		return access_token_secret;
	}
	
	public static Twitter getTwitterObject(){
		return twitter;
	}
	
	public static void main(String[] args) {
		System.out.println("horse");

	}

}
