package main

import (
	"fmt"
	"net/http"
	"os"
	"strings"
	"time"

	routers "twitterApi/router"
	"twitterApi/utils"

	"github.com/gorilla/mux"
	logger "github.com/sirupsen/logrus"
)

func main() {
	errInfo := ""

	// Initialize environment variables from .env file
	if err := utils.InitEnvVars(); err != nil {
		errInfo = fmt.Sprintf("failed to initialize environment variables: %s", err.Error())
		logger.WithFields(logger.Fields{
			"error": err,
		}).Fatal(errInfo)
	}

	// Get the port from the environment variable
	port := os.Getenv("PORT")
	if len(strings.TrimSpace(port)) == 0 {
		port = utils.SERVER_PORT
		errInfo = fmt.Sprintf("port environment variable not set. defaulting to %s", port)
		logger.Println(errInfo)
	}

	// Set up the routers
	router := mux.NewRouter()
	routers.RankWordsRouters(router)

	errInfo = fmt.Sprintf("started API on port: %s", port)
	errInfo1 := fmt.Sprintf("server started at: %s", time.Now().Format(time.RFC3339))

	logger.Print(errInfo)
	logger.Print(errInfo1)

	// Create a http.Server and start listening to port 8080
	logger.Fatal(http.ListenAndServe(":"+port, router))
}
