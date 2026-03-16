import React from 'react'
import { Link } from "react-router-dom"

function Land() {

  const container = {
    textAlign: "center",
    marginTop: "80px"
  }

  const card = {
    border: "1px solid #ddd",
    borderRadius: "10px",
    padding: "30px",
    margin: "20px",
    display: "inline-block",
    width: "220px",
    boxShadow: "0px 4px 10px rgba(0,0,0,0.1)"
  }

  const button = {
    display: "inline-block",
    marginTop: "15px",
    padding: "10px 20px",
    background: "#007BFF",
    color: "white",
    textDecoration: "none",
    borderRadius: "5px"
  }

  return (
    <div style={container}>

      <h1>Finance Calculator</h1>
      <p>Select the calculator you want</p>

      <div style={card}>
        <h2>Loan Calculator</h2>
        <Link to="/loan" style={button}>Go</Link>
      </div>

      <div style={card}>
        <h2>Deposit Calculator</h2>
        <Link to="/deposit" style={button}>Go</Link>
      </div>

    </div>
  )
}

export default Land