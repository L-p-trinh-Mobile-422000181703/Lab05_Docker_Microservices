import unittest
from app import app

class TestApp(unittest.TestCase):
    def setUp(self):
        self.client = app.test_client()

    def test_index(self):
        response = self.client.get("/")
        self.assertEqual(response.status_code, 200)
        data = response.get_json()
        self.assertIn("message", data)

    def test_version(self):
        response = self.client.get("/")
        data = response.get_json()
        self.assertEqual(data["version"], "1.0.0")

if __name__ == "__main__":
    unittest.main()
