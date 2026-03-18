from flask import Flask, request, jsonify
import redis
import json

app = Flask(__name__)
r = redis.Redis(host="redis", port=6379)

@app.route("/", methods=["GET"])
def index():
    return jsonify(message="Voting App - Choose 'a' (Cats) or 'b' (Dogs)")

@app.route("/vote", methods=["POST"])
def vote():
    data = request.get_json()
    voter_id = data.get("voter_id", "anonymous")
    choice = data.get("vote")
    if choice not in ("a", "b"):
        return jsonify(error="Vote must be 'a' or 'b'"), 400
    r.rpush("votes", json.dumps({"voter_id": voter_id, "vote": choice}))
    return jsonify(status="ok", voter_id=voter_id, vote=choice)

if __name__ == "__main__":
    app.run(host="0.0.0.0", port=5000)
