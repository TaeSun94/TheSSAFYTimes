import axios from "axios";

export default axios.create({
  baseURL: "http://i3c208.p.ssafy.io:8888/ssafience/api",
  headers: {
    "Content-type": "application/json"
  }
});
// http://i3c208.p.ssafy.io:8888/ssafience/swagger-ui.html