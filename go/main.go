package main

import (
	"bytes"
	"encoding/base64"
	"fmt"
	"io"
	"net/http"
)

type postBody struct {
	from string
	to   []string
	text string
}

func (pb postBody) MarshalJSON() ([]byte, error) {
	return []byte(fmt.Sprintf(`{"From":"%s","To":%s,"Text":"%s"}`, pb.from, pb.to, pb.text)), nil
}

func main() {
	client := http.DefaultClient
	// build request
	pb := postBody{
		from: "TEST",
		to:   []string{"+15551234"},
		text: "Test message",
	}
	body, err := pb.MarshalJSON()
	if err != nil {
		panic(err)
	}
	req, err := http.NewRequest(
		http.MethodPost,
		"https://api.genericmobile.se/SmsGateway/api/v1/Message",
		bytes.NewBuffer(body),
	)
	if err != nil {
		panic(err)
	}
	req.Header.Add("Authorization", "Basic "+base64.StdEncoding.EncodeToString([]byte("USERNAME:PASSWORD")))
	req.Header.Add("Content-Type", "application/json")

	// do http request
	resp, err := client.Do(req)
	if err != nil {
		panic(err)
	}
	defer resp.Body.Close()

	// read response body
	b, err := io.ReadAll(resp.Body)
	if err != nil {
		panic(err)
	}

	fmt.Println(resp.StatusCode)
	fmt.Println(string(b))
}
