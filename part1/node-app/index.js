const express = require("express");
const mysql = require("mysql2/promise");

const app = express();
const PORT = 3000;

// Bài 4: Express đơn giản
app.get("/", (req, res) => {
  res.json({ message: "Hello from Node.js Express!" });
});

// Bài 8: Kết nối Node.js với MySQL
app.get("/db", async (req, res) => {
  try {
    const connection = await mysql.createConnection({
      host: "mysql",
      user: "user",
      password: "password",
      database: "mydb",
    });
    const [rows] = await connection.execute("SELECT NOW() AS current_time");
    await connection.end();
    res.json({ message: "Connected to MySQL!", data: rows[0] });
  } catch (err) {
    res.status(500).json({ error: err.message });
  }
});

app.listen(PORT, () => {
  console.log(`Node.js app running on port ${PORT}`);
});
