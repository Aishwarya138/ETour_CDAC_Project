export const isLoggedIn = () => {
  return localStorage.getItem("customerId") !== null;
};

export const getCustomerId = () => {
  return localStorage.getItem("customerId");
};

export const logout = () => {
  localStorage.clear();
  window.location.href = "/login";
};
