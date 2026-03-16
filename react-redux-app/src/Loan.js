import React from 'react'
import { Link } from "react-router-dom"

function Loan() {
  const containerStyle = {
    textAlign: "center",
    marginTop: "50px"
  }

  const cardStyle = {
    border: "1px solid #ddd",
    borderRadius: "10px",
    padding: "20px",
    margin: "20px",
    display: "inline-block",
    width: "200px",
    boxShadow: "0px 4px 8px rgba(0,0,0,0.1)"
  }

  const buttonStyle = {
    display: "inline-block",
    marginTop: "10px",
    padding: "10px 20px",
    backgroundColor: "#007BFF",
    color: "white",
    textDecoration: "none",
    borderRadius: "5px"
  }

  return (
    <div style={containerStyle}>

      <h1>Loan EMI Calculator</h1>

      <div style={cardStyle}>
        <h2>Home Loan</h2>
        <Link to="/home" style={buttonStyle}>Calculate</Link>
      </div>

      <div style={cardStyle}>
        <h2>Car Loan</h2>
        <Link to="/car" style={buttonStyle}>Calculate</Link>
      </div>

      <div style={cardStyle}>
        <h2>Personal Loan</h2>
        <Link to="/personal" style={buttonStyle}>Calculate</Link>
      </div>

    </div>
  )
}

export default Loan