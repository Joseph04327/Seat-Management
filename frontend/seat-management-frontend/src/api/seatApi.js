import axios from "axios";

const API_URL = "http://localhost:8080";

export const getSeats = () => {
  return axios.get(`${API_URL}/seats`);
};

export const assignSeat = (employeeId, seatId) => {
  return axios.put(
    `${API_URL}/employees/${employeeId}/seat/${seatId}`
  );
};

export const clearSeat = (seatId) => {
  return axios.put(
    `${API_URL}/seats/${seatId}/clear`
  );
};