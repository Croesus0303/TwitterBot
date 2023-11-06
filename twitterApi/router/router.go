package routers

import (
	"twitterApi/service"

	"github.com/gorilla/mux"
)

// RankWordsRouters sets up the routes for the API
func RankWordsRouters(r *mux.Router) {
	// Project Codes
	r.HandleFunc("/sendTweet", service.SendTweet).Methods("POST")
}
