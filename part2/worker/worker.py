import redis
import psycopg2
import json
import time

def main():
    while True:
        try:
            r = redis.Redis(host="redis", port=6379)
            conn = psycopg2.connect(
                host="postgres", user="postgres", password="postgres", dbname="votes"
            )
            cur = conn.cursor()
            cur.execute(
                "CREATE TABLE IF NOT EXISTS votes "
                "(id SERIAL PRIMARY KEY, voter_id VARCHAR(255), vote VARCHAR(1))"
            )
            conn.commit()
            print("Worker connected. Processing votes...")
            break
        except Exception as e:
            print(f"Waiting for services... {e}")
            time.sleep(2)

    while True:
        data = r.lpop("votes")
        if data:
            vote = json.loads(data)
            cur.execute(
                "INSERT INTO votes (voter_id, vote) VALUES (%s, %s)",
                (vote["voter_id"], vote["vote"]),
            )
            conn.commit()
            print(f"Processed vote: {vote}")
        else:
            time.sleep(1)

if __name__ == "__main__":
    main()
