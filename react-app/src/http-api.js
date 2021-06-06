import axios from "axios";

export default axios.create({
  baseURL: "http://192.168.160.87:31003",
  headers: {
    "Content-type": "application/json"
  }
});
