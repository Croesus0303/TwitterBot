package service

import (
	"bytes"
	"fmt"
	"net/http"

	"github.com/dghubble/oauth1"
)

type Tweet struct {
	text string
}

func SendTweet(w http.ResponseWriter, r *http.Request) {
	w.Header().Set("Content-Type", "application/json")
	consumerKey := "M7SAfYTupq3NkovFAwRhfEL17"
	consumerSecret := "T6FrAUOtb3PI4ZKweJSopajjQsxjsOe4NZdQuAaQkvtrr0g52Q"
	accessToken := "1569026232003563525-i4ff5hJQztalGBse8nYV52gZnHpGjk"
	accessTokenSecret := "Q6lw5GyKcumb8ullgCpGytJXGlqfaKKnm4B7n09suMjyl"

	url := "https://api.twitter.com/2/tweets"

	// Create an OAuth1 configuration
	config := oauth1.NewConfig(consumerKey, consumerSecret)
	token := oauth1.NewToken(accessToken, accessTokenSecret)

	// Create an HTTP client with the OAuth1 configuration
	httpClient := config.Client(oauth1.NoContext, token)

	jsonPayload := []byte(`{"text": "Test"}`)

	req, err := http.NewRequest("POST", url, bytes.NewBuffer(jsonPayload))
	if err != nil {
		fmt.Println("Error creating request:", err)
		return
	}
	req.Header.Add("Content-Type", "application/json")

	// Sign the request with OAuth 1.0 headers
	resp, err := httpClient.Do(req)
	if err != nil {
		fmt.Println("Error sending request:", err)
		return
	}
	defer resp.Body.Close()

	// Check the response
	if resp.StatusCode == http.StatusOK {
		fmt.Println("Request was successful")
	} else {
		fmt.Printf("Request failed with status code: %d\n", resp.StatusCode)
	}

}
