import configparser
import logging

import tweepy
import requests
import os

from twitterApi.constants.constants import Constants

config = configparser.ConfigParser(interpolation=None)
config.read('config.ini')


class Service:

    def send_tweet(tweet):
        media_id = None
        file_name = None

        api_key = str(config["twitter"]["api_key"])
        api_key_secret = str(config["twitter"]["api_key_secret"])
        access_token = str(config["twitter"]["access_token"])
        access_token_secret = str(config["twitter"]["access_token_secret"])
        bearer_token = config["twitter"]["bearer_token"]

        if tweet.imageUrl is not None:
            try:
                file_name = download_image(tweet.imageUrl)
                logging.info(Constants.LoggingMessages.downloadImageMessage + tweet.imageUrl)
            except Exception as e :
                logging.warning(Constants.LoggingMessages.downloadImageUnsuccessMessage + str(e))

            try:
                media_id = get_media_id(tweet, api_key, api_key_secret,
                                    access_token, access_token_secret, file_name)
                logging.info(Constants.LoggingMessages.uploadImageSuccessMessage)
            except Exception as e :
                logging.warning(Constants.LoggingMessages.uploadImageUnsuccessMessage+ str(e))

        api = tweepy.Client(bearer_token=bearer_token, consumer_key=api_key,
                            consumer_secret=api_key_secret,
                            access_token=access_token,
                            access_token_secret=access_token_secret, wait_on_rate_limit=True)

        if media_id is not None:
            try:
                api.create_tweet(text=tweet.text, media_ids=[media_id])
                os.remove(file_name)
                logging.info(Constants.LoggingMessages.createTweetSuccessMessage)
            except Exception as e:
                logging.warning(Constants.LoggingMessages.createTweetUnSuccessMessage + str(e))
        else:
            api.create_tweet(text=tweet.text)


def download_image(url):
    # Send an HTTP GET request to the URL

    save_directory = 'twitterApi/downloaded_images'

    response = requests.get(url)

    # Check if the request was successful (status code 200)
    if response.status_code == 200:
        # Extract the file name from the URL
        file_name = os.path.join(save_directory, url.split("/")[-1])

        # Save the image to the specified directory
        with open(file_name, 'wb') as file:
            file.write(response.content)

        return file_name
    else:
        print(f"Failed to download image from {url}. Status code: {response.status_code}")
        return None


def get_media_id(tweet, api_key, api_key_secret, access_token, access_token_secret, file_name):
    if tweet.imageUrl is not None:
        auth = tweepy.OAuth1UserHandler(api_key, api_key_secret)
        auth.set_access_token(
            access_token,
            access_token_secret,
        )
        api = tweepy.API(auth)

        media = api.media_upload(filename=file_name)

        media_id = media.media_id

        return media_id
