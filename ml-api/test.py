# Test keliye hai delete after use
import requests

url = "http://127.0.0.1:5000/predict"

data = {
    
    "message": "when will exam results declared"
}

response = requests.post(url, json=data)

print(response.json())