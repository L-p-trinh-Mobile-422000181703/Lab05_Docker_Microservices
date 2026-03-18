const express = require("express");
const { Pool } = require("pg");

const app = express();
const pool = new Pool({
  host: "postgres",
  user: "postgres",
  password: "postgres",
  database: "votes",
});

app.get("/", (req, res) => {
  res.json({ message: "Result App - View voting results" });
});

app.get("/results", async (req, res) => {
  try {
    const result = await pool.query(
      "SELECT vote, COUNT(*) as count FROM votes GROUP BY vote"
    );
    res.json(result.rows);
  } catch (err) {
    res.status(500).json({ error: err.message });
  }
});

app.listen(5001, () => console.log("Result app on port 5001"));
