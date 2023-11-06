package utils

import "os"

const (
	SERVER_PORT           = "8080"
	X_ACCESS_TOKEN        = "1569026232003563525-i4ff5hJQztalGBse8nYV52gZnHpGjk"
	X_ACCESS_TOKEN_SECRET = "Q6lw5GyKcumb8ullgCpGytJXGlqfaKKnm4B7n09suMjyl"
	X_API_KEY             = "M7SAfYTupq3NkovFAwRhfEL17"
	X_API_SECRET_KEY      = "T6FrAUOtb3PI4ZKweJSopajjQsxjsOe4NZdQuAaQkvtrr0g52Q"
	BEARER                = "AAAAAAAAAAAAAAAAAAAAAPyrpwEAAAAAd9JJbHNQnEIFEPAcWXjnJkdiQww%3DXBuT2M4wvcCSqUnnRzctfrPvBiJlYlpG52MJ8E5pEil7n3dSsr"
	Endpoint_X            = "https://api.twitter.com/2/tweets"
)

// SetEnv sets the environment variables with string values
func SetEnv(env map[string]string) {
	for k, v := range env {
		os.Setenv(k, v)
	}
}

// InitEnvVars initialize environment variables
func InitEnvVars() error {
	// Set environment variables with string values
	SetEnv(map[string]string{
		"PORT":                SERVER_PORT,
		"ACCESS_TOKEN":        X_ACCESS_TOKEN,
		"ACCESS_TOKEN_SECRET": X_ACCESS_TOKEN_SECRET,
		"X_API_KEY":           X_API_KEY,
		"X_API_SECRET_KEY":    X_API_SECRET_KEY,
		"BEARER":              BEARER,
		"Endpoint_X":          Endpoint_X,
	})
	return nil
}
