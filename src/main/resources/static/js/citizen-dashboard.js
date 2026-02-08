const API_URL = "http://localhost:8080/api";

const token = localStorage.getItem("token");

if (!token) {
  window.location.href = "/login.html";
}

document.getElementById("userEmail").textContent =
  localStorage.getItem("email");

async function loadReports() {
  const response = await fetch(`${API_URL}/reports/my-reports`, {
    headers: { Authorization: `Bearer ${token}` }
  });

  if (!response.ok) return;

  const reports = await response.json();
  document.getElementById("reportsContainer").innerHTML =
    reports.map(r => `<div class="report-card">${r.description}</div>`).join("");
}

function logout() {
  localStorage.clear();
  window.location.href = "/login.html";
}

loadReports();
