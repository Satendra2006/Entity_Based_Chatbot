# Entity Based Chatbot

An AI-powered **Entity Based Chatbot** developed as a team project that can understand user queries, identify relevant entities, and provide appropriate responses. The project combines a machine learning model with a Flask API backend and a Java-based application interface.

## Project Overview

The main objective of this project is to build a chatbot system that can process user inputs, extract meaningful entities, and generate responses based on the identified information.

The project consists of multiple modules:

* Dataset creation and preprocessing
* Entity recognition model development
* Flask API for model integration
* Java backend implementation
* HTML-based frontend interface

## My Contribution

I worked on the AI/ML part of the project:

### Dataset Development

* Created and prepared the dataset required for training the chatbot model.
* Performed data preprocessing and formatting for model training.
* Structured the data to support entity-based query understanding.

### Model Development

* Built and trained the machine learning model for entity identification.
* Implemented the pipeline for processing user input and generating predictions.
* Tested and improved the model performance.

### Flask API Development

* Developed a Flask API to connect the trained model with the application.
* Created API endpoints to receive user queries and return chatbot responses.
* Integrated the ML model with the backend workflow.

## Team Contributions

Other modules of the project were developed by my teammates:

### Backend

* Implemented using **Java**.
* Developed using **Eclipse IDE**.
* Responsible for application logic and communication with the Flask API.

### Frontend

* Built using **HTML**.
* Provides the user interface for interacting with the chatbot.

## Technologies Used

* Python
* Machine Learning
* Flask
* Dataset Processing
* Java
* Eclipse IDE
* HTML

## System Architecture

```
User
 |
HTML Frontend
 |
Java Backend
 |
Flask API
 |
Entity Recognition Model
 |
Response Generation
```

## Features

* User interaction through a chatbot interface
* Entity-based query understanding
* ML model integration through API
* Separation of frontend, backend, and AI modules
* Real-time response generation

## How It Works

1. User enters a query through the frontend.
2. The request is processed by the Java backend.
3. The backend communicates with the Flask API.
4. Flask sends the input to the trained entity recognition model.
5. The model identifies relevant entities.
6. The response is returned back to the user.

## Future Improvements

* Improve model accuracy with larger datasets.
* Add support for more entity types.
* Deploy the Flask API on a cloud platform.
* Add conversational memory for better interactions.

## Team Project

This project was developed collaboratively, combining machine learning, backend development, and frontend development to create a complete chatbot application.
