package utils

import (
	"net/http"

	"github.com/dghubble/oauth1"
)

func SetAuthHeaders(consumerKey, consumerSecret, accessToken, accessTokenSecret string) *http.Client {

	config := oauth1.NewConfig(consumerKey, consumerSecret)
	token := oauth1.NewToken(accessToken, accessTokenSecret)

	// Create an HTTP client with the OAuth1 configuration
	httpClient := config.Client(oauth1.NoContext, token)

	return httpClient
}
