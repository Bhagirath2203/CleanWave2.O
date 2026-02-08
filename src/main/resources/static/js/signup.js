const API_URL = "http://localhost:8080/api";

document.getElementById("signupForm").addEventListener("submit", async (e) => {
  e.preventDefault();

  const username = document.getElementById("username").value;
  const email = document.getElementById("email").value;
  const password = document.getElementById("password").value;
  const role = document.getElementById("role").value; // ADMIN / WORKER / CITIZEN

  try {
    const response = await fetch(`${API_URL}/auth/signup`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify({ username, email, password, role })
    });

    if (!response.ok) {
      showError("Signup failed. Email may already exist.");
      return;
    }

    alert("Signup successful. Please login.");
    window.location.href = "/login.html";

  } catch (err) {
    showError("Network error. Please try again.");
  }
});

function showError(message) {
  const errorDiv = document.getElementById("errorMessage");
  errorDiv.textContent = message;
  errorDiv.classList.add("show");
}
