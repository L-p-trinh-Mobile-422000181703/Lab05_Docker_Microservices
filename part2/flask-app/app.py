import os
import socket
from flask import Flask, jsonify

app = Flask(__name__)

@app.route("/")
def hello():
    hostname = socket.gethostname()
    return jsonify(
        message="Hello from Flask!",
        hostname=hostname,
        server_id=os.environ.get("SERVER_ID", "unknown"),
    )

if __name__ == "__main__":
    app.run(host="0.0.0.0", port=5000)
