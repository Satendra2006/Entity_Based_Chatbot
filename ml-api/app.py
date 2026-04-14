from flask import Flask, request, jsonify
import pickle
import spacy
import string

app = Flask(__name__)

# load NLP model
nlp = spacy.load("en_core_web_sm")

# load ML model
model = pickle.load(open("model.pkl", "rb"))
vectorizer = pickle.load(open("vectorizer.pkl", "rb"))

# clean text (same as training)
def clean_text(text):
    text = text.lower()
    text = "".join([c for c in text if c not in string.punctuation])
    return text

# intent prediction
def predict_intent(text):
    text = clean_text(text)
    vec = vectorizer.transform([text])
    return model.predict(vec)[0]

# entity extraction (NLP)
def extract_entity(text):
    doc = nlp(text.lower())
    
    entities = []
    
    for chunk in doc.noun_chunks:
        clean_chunk = " ".join([
            token.text for token in chunk 
            if not token.is_stop
        ])
        if clean_chunk:
            entities.append(clean_chunk)
    
    if entities:
        return max(entities, key=len)
    
    return None

# fallback entity
def fallback_entity(intent):
    fallback_map = {
        "timing": "college timing",
        "placement": "placement details",
        "fees": "fee structure"
    }
    return fallback_map.get(intent, None)

# main route
@app.route("/predict", methods=["POST"])
def predict():
    data = request.get_json()
    user_input = data.get("message", "")

    intent = predict_intent(user_input)
    entity = extract_entity(user_input)

    if entity is None:
        entity = fallback_entity(intent)

    return jsonify({
        "intent": intent,
        "entity": entity
    })

# health check
@app.route("/")
def home():
    return "Chatbot API running 🚀"

# run app
if __name__ == "__main__":
    app.run(debug=True)