const express = require("express");
const mongoose = require("mongoose");

const app = express();
app.use(express.json());

mongoose
  .connect("mongodb://mongodb:27017/mydb")
  .then(() => console.log("Connected to MongoDB"))
  .catch((err) => console.error("MongoDB connection error:", err));

const ItemSchema = new mongoose.Schema({
  name: String,
  createdAt: { type: Date, default: Date.now },
});
const Item = mongoose.model("Item", ItemSchema);

app.get("/", (req, res) => {
  res.json({ message: "Node.js + MongoDB API" });
});

app.get("/items", async (req, res) => {
  const items = await Item.find();
  res.json(items);
});

app.post("/items", async (req, res) => {
  const item = new Item({ name: req.body.name });
  await item.save();
  res.status(201).json(item);
});

app.listen(3000, () => console.log("API running on port 3000"));
