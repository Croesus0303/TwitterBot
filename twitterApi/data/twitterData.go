package data

import (
	"bytes"
	"fmt"
	"net/http"
	"os"

	"twitterApi/utils"
)

func SendTweetDataCaller(text string) *http.Response {

	jsonPayload := []byte(`{"text": "Test"}`)

	endpoint := os.Getenv("Endpoint_X")

	httpClient := utils.SetAuthHeaders(os.Getenv("X_API_KEY"), os.Getenv("X_API_SECRET_KEY"),
		os.Getenv("ACCESS_TOKEN"), os.Getenv("ACCESS_TOKEN_SECRET"))
	println(os.Getenv("X_API_KEY"))

	req, err := http.NewRequest("POST", endpoint, bytes.NewBuffer(jsonPayload))
	if err != nil {
		fmt.Println("Error creating request:", err)

	}
	req.Header.Add("Content-Type", "application/json")

	// Sign the request with OAuth 1.0 headers
	resp, err := httpClient.Do(req)
	return resp
}
