import axios from "axios";

export default axios.create({
  baseURL: "http://localhost:8270/admin/",
  responseType: "json",
  headers: {
  },
});