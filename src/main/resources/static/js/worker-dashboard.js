const API_URL = "http://localhost:8080/api";

const token = localStorage.getItem("token");
const role = localStorage.getItem("role");

if (!token || role !== "WORKER") {
  window.location.href = "/login.html";
}

document.getElementById("userEmail").textContent =
  localStorage.getItem("email");

async function loadAssignments() {
  const response = await fetch(`${API_URL}/worker/my-assignments`, {
    headers: { Authorization: `Bearer ${token}` }
  });

  if (!response.ok) return;

  const assignments = await response.json();
  document.getElementById("totalAssignments").textContent = assignments.length;
}

function logout() {
  localStorage.clear();
  window.location.href = "/login.html";
}

loadAssignments();
