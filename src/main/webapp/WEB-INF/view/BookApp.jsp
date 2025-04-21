<%--
  Created by IntelliJ IDEA.
  User: acer
  Date: 4/21/2025
  Time: 1:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Book Appointment - Doctor Mero Sathi</title>
    <style>
        body {
            font-family: 'Segoe UI', sans-serif;
            background-color: #FFFFFF; /* Pure White */
            margin: 0;
            padding: 20px;
            display: flex;
            flex-direction: column;
            align-items: center;
        }

        .header-area {
            text-align: center;
            margin-bottom: 30px;
        }

        .header-area svg { /* Placeholder for the house icon */
            fill: #2A7F9D; /* Primary Blue */
            width: 50px;
            height: 50px;
            margin-bottom: 10px;
        }

        .header-area svg .plus { /* Placeholder for the plus inside the house */
            fill: #FFFFFF; /* Pure White */
        }

        .header-area h1 {
            color: #2A7F9D; /* Primary Blue */
            margin-bottom: 5px;
        }

        .header-area h2 {
            color: #333333; /* Dark Charcoal */
            font-size: 1.5rem;
            font-weight: normal;
        }

        .appointment-form {
            width: 80%; /* Adjust width as needed */
            max-width: 500px;
        }

        .form-group {
            margin-bottom: 15px;
        }

        .form-group label {
            display: block;
            margin-bottom: 5px;
            color: #333333; /* Dark Charcoal */
            font-weight: bold;
        }

        .form-group input[type="text"],
        .form-group input[type="email"],
        .form-group input[type="tel"],
        .form-group input[type="date"],
        .form-group textarea {
            width: 100%;
            padding: 10px;
            border: 1px solid #D3DCE0; /* Slightly darker light gray */
            border-radius: 5px;
            font-size: 1rem;
            color: #333333; /* Dark Charcoal */
            box-sizing: border-box;
        }

        .form-group input::placeholder,
        .form-group textarea::placeholder {
            color: rgba(51, 51, 51, 0.7); /* Lighter Dark Charcoal for placeholder */
        }

        .form-group .date-input-wrapper {
            position: relative;
        }

        .form-group .date-input-wrapper input[type="date"] {
            padding-right: 35px;
        }

        .form-group .date-input-wrapper .calendar-icon {
            position: absolute;
            top: 50%;
            right: 10px;
            transform: translateY(-50%);
            color: #5DB4B8; /* Soft Teal */
            cursor: pointer;
        }

        .book-appointment-button {
            background-color: #FF8A7D; /* Warm Coral */
            color: #FFFFFF; /* Pure White */
            border: none;
            padding: 12px 25px;
            border-radius: 5px;
            cursor: pointer;
            font-size: 1.1rem;
            transition: background-color 0.3s ease;
        }

        .book-appointment-button:hover {
            background-color: #E67A6E; /* Slightly darker coral on hover */
        }
    </style>
</head>
<body>
<div class="header-area">
    <svg viewBox="0 0 24 24">
        <path d="M12 2L2 7l10 5 10-5-10-5zM2 17h20v2H2v-2zM12 12L2 17h10v5zM12 12l10 5h-10v5z"/>
        <path class="plus" fill="#FFFFFF" d="M11 10h2v3h3v2h-3v3h-2v-3H8v-2h3v-3z"/>
    </svg>
    <h1>Doctor Mero Sathi</h1>
    <h2>Book Appointment</h2>
</div>

<form class="appointment-form">
    <div class="form-group">
        <label for="full_name">Full Name</label>
        <input type="text" id="full_name" name="full_name" placeholder="Your Full Name">
    </div>
    <div class="form-group">
        <label for="email">Email Address</label>
        <input type="email" id="email" name="email" placeholder="Your Email Address">
    </div>
    <div class="form-group">
        <label for="phone">Phone Number</label>
        <input type="tel" id="phone" name="phone" placeholder="Your Phone Number">
    </div>
    <div class="form-group date-input-wrapper">
        <label for="appointment_date">Appointment Date</label>
        <input type="date" id="appointment_date" name="appointment_date">
        <span class="calendar-icon">&#128197;</span>
    </div>
    <div class="form-group">
        <label for="symptoms">Symptoms / Notes</label>
        <textarea id="symptoms" name="symptoms" rows="4" placeholder="Describe your symptoms or any notes for the doctor"></textarea>
    </div>
    <button type="submit" class="book-appointment-button">Book Appointment</button>
</form>
</body>
</html>