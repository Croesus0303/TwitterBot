package config

import (
	// other imports
	"log"
	"sync"

	"github.com/dghubble/go-twitter/twitter"
	"github.com/dghubble/oauth1"
)

var lock = &sync.Mutex{}

// Credentials stores all of our access/consumer tokens
// and secret keys needed for authentication against
// the twitter REST API.
type Credentials struct {
	ConsumerKey       string
	ConsumerSecret    string
	AccessToken       string
	AccessTokenSecret string
	BEARER            string
}

var clientInstance *twitter.Client

// getClient is a helper function that will return a twitter client
// that we can subsequently use to send tweets, or to stream new tweets
// this will take in a pointer to a Credential struct which will contain
// everything needed to authenticate and return a pointer to a twitter Client
// or an error
func GetClient(creds *Credentials) (*twitter.Client, error) {

	if clientInstance == nil {
		lock.Lock()
		defer lock.Unlock()
		if clientInstance == nil {
			config := oauth1.NewConfig(creds.ConsumerKey, creds.ConsumerSecret)
			// Pass in your Access Token and your Access Token Secret
			token := oauth1.NewToken(creds.AccessToken, creds.AccessTokenSecret)

			httpClient := config.Client(oauth1.NoContext, token)
			client := twitter.NewClient(httpClient)

			// Verify Credentials
			verifyParams := &twitter.AccountVerifyParams{
				SkipStatus:   twitter.Bool(true),
				IncludeEmail: twitter.Bool(true),
			}

			// we can retrieve the user and verify if the credentials
			// we have used successfully allow us to log in!
			user, _, err := client.Accounts.VerifyCredentials(verifyParams)
			if err != nil {
				return nil, err
			}
			clientInstance := client
			log.Printf("User's ACCOUNT:\n%+v\n", user)
			return clientInstance, nil
		} else {
			// do nothing
		}

	} else {

	}

	return clientInstance, nil
	// Pass in your consumer key (API Key) and your Consumer Secret (API Secret)

}
