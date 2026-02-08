const API_URL = "http://localhost:8080/api";

const token = localStorage.getItem("token");
const role = localStorage.getItem("role");

if (!token || role !== "ADMIN") {
  window.location.href = "/login.html";
}

document.getElementById("userEmail").textContent =
  localStorage.getItem("email");

let allReports = [];
let workers = [];

async function loadReports() {
  const response = await fetch(`${API_URL}/admin/reports`, {
    headers: { Authorization: `Bearer ${token}` }
  });

  if (!response.ok) return;

  allReports = await response.json();
  updateStats();
  displayReports(allReports);
}

async function loadWorkers() {
  const response = await fetch(`${API_URL}/admin/workers`, {
    headers: { Authorization: `Bearer ${token}` }
  });

  if (!response.ok) return;

  workers = await response.json();
  populateWorkerSelect();
}

function updateStats() {
  document.getElementById("totalReports").textContent = allReports.length;
  document.getElementById("openReports").textContent =
    allReports.filter(r => r.status === "OPEN").length;
  document.getElementById("inProgressReports").textContent =
    allReports.filter(r => r.status === "IN_PROGRESS").length;
  document.getElementById("closedReports").textContent =
    allReports.filter(r => r.status === "CLOSED").length;
}

function logout() {
  localStorage.clear();
  window.location.href = "/login.html";
}

loadReports();
loadWorkers();
