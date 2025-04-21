<%--
  Created by IntelliJ IDEA.
  User: acer
  Date: 4/21/2025
  Time: 9:02 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>About Us - Doctor Mero Saathi</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    <style>
        :root {
            --primary-blue: #2A7F9D;
            --accent-coral: #FF8A7D;
            --light-blue: #E1F0F5;
            --dark-gray: #333333;
            --medium-gray: #555555;
            --light-gray: #f8f9fa;
        }

        body {
            font-family: 'Segoe UI', sans-serif;
            margin: 0;
            padding: 0;
            background-color: var(--light-gray);
            color: var(--dark-gray);
            line-height: 1.6;
        }

        .about-container {
            max-width: 1000px;
            margin: 40px auto;
            background-color: white;
            padding: 40px;
            border-radius: 12px;
            box-shadow: 0 4px 20px rgba(0,0,0,0.08);
        }

        .page-header {
            text-align: center;
            margin-bottom: 40px;
        }

        .page-header h1 {
            color: var(--primary-blue);
            font-size: 2.5rem;
            margin-bottom: 15px;
            position: relative;
            display: inline-block;
        }

        .page-header h1:after {
            content: '';
            position: absolute;
            bottom: -10px;
            left: 50%;
            transform: translateX(-50%);
            width: 80px;
            height: 4px;
            background-color: var(--accent-coral);
            border-radius: 2px;
        }

        .page-header p.subtitle {
            color: var(--medium-gray);
            font-size: 1.1rem;
            max-width: 700px;
            margin: 0 auto;
        }

        .section {
            margin-bottom: 40px;
        }

        .section h2 {
            color: var(--primary-blue);
            margin-bottom: 20px;
            display: flex;
            align-items: center;
            gap: 10px;
        }

        .section h2 i {
            color: var(--accent-coral);
        }

        .section p {
            color: var(--medium-gray);
            margin-bottom: 15px;
        }

        .mission-values {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
            gap: 25px;
            margin-top: 30px;
        }

        .value-card {
            background-color: var(--light-blue);
            padding: 25px;
            border-radius: 8px;
            text-align: center;
            transition: transform 0.3s ease;
        }

        .value-card:hover {
            transform: translateY(-5px);
        }

        .value-card i {
            font-size: 2.5rem;
            color: var(--primary-blue);
            margin-bottom: 15px;
        }

        .value-card h3 {
            color: var(--primary-blue);
            margin-bottom: 10px;
        }

        .team-members {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
            gap: 30px;
            margin-top: 30px;
        }

        .team-card {
            text-align: center;
        }

        .team-img {
            width: 150px;
            height: 150px;
            border-radius: 50%;
            object-fit: cover;
            margin: 0 auto 15px;
            border: 5px solid var(--light-blue);
        }

        .team-card h3 {
            color: var(--primary-blue);
            margin-bottom: 5px;
        }

        .team-card p.position {
            color: var(--accent-coral);
            font-weight: 600;
            margin-bottom: 10px;
        }

        .contact-info {
            display: flex;
            flex-wrap: wrap;
            gap: 30px;
            margin-top: 20px;
        }

        .contact-method {
            display: flex;
            align-items: center;
            gap: 10px;
            color: var(--medium-gray);
        }

        .contact-method i {
            color: var(--primary-blue);
            font-size: 1.2rem;
            width: 25px;
        }

        @media (max-width: 768px) {
            .about-container {
                padding: 25px;
                margin: 20px;
            }

            .page-header h1 {
                font-size: 2rem;
            }
        }
    </style>
</head>
<body>
<%@ include file="/WEB-INF/view/navbar.jsp" %>
<div class="about-container">
    <div class="page-header">
        <h1>About Doctor Mero Saathi</h1>
        <p class="subtitle">Bridging the gap between patients and healthcare providers through innovative technology</p>
    </div>

    <div class="section">
        <h2><i class="fas fa-heartbeat"></i> Our Mission</h2>
        <p>
            At <strong>Doctor Mero Saathi</strong>, we're revolutionizing healthcare accessibility in Nepal.
            Our platform connects patients with qualified medical professionals, making quality healthcare
            just a click away.
        </p>

        <div class="mission-values">
            <div class="value-card">
                <i class="fas fa-hand-holding-heart"></i>
                <h3>Compassionate Care</h3>
                <p>Putting patient well-being at the heart of everything we do</p>
            </div>

            <div class="value-card">
                <i class="fas fa-lightbulb"></i>
                <h3>Innovation</h3>
                <p>Leveraging technology to solve healthcare challenges</p>
            </div>

            <div class="value-card">
                <i class="fas fa-users"></i>
                <h3>Community</h3>
                <p>Building a network of trusted healthcare providers</p>
            </div>
        </div>
    </div>

    <div class="section">
        <h2><i class="fas fa-users"></i> Our Team</h2>
        <p>
            We are a dedicated team of healthcare technology professionals committed to revolutionizing medical services in Nepal through innovative digital solutions.
        </p>

        <div class="team-members">
            <!-- Team Leader -->
            <div class="team-card">
                <img src="https://via.placeholder.com/150/e1f0f5/2a7f9d?text=AS" alt="Aditya Sigdel" class="team-img">
                <h3>Aditya Sigdel</h3>
                <p class="position">Team Leader</p>
                <p>Project vision and technical architecture</p>
            </div>

            <!-- Utsab Lama (Tamang) -->
            <div class="team-card">
                <img src="https://via.placeholder.com/150/e1f0f5/2a7f9d?text=UL" alt="Utsab Lama" class="team-img">
                <h3>Utsab Lama (Tamang)</h3>
                <p class="position">Lead Developer</p>
                <p>Backend systems and database</p>
            </div>

            <!-- Ankit Baral -->
            <div class="team-card">
                <img src="https://via.placeholder.com/150/e1f0f5/2a7f9d?text=AB" alt="Ankit Baral" class="team-img">
                <h3>Ankit Baral</h3>
                <p class="position">Frontend Developer</p>
                <p>User interface and experience</p>
            </div>

            <!-- Ashnab Pokharel -->
            <div class="team-card">
                <img src="https://via.placeholder.com/150/e1f0f5/2a7f9d?text=AP" alt="Ashnab Pokharel" class="team-img">
                <h3>Ashnab Pokharel</h3>
                <p class="position">UI/UX Designer</p>
                <p>Visual design and interaction</p>
            </div>

            <!-- Rabindra Aryal -->
            <div class="team-card">
                <img src="https://via.placeholder.com/150/e1f0f5/2a7f9d?text=RA" alt="Rabindra Aryal" class="team-img">
                <h3>Rabindra Aryal</h3>
                <p class="position">Quality Assurance</p>
                <p>Testing and system validation</p>
            </div>
        </div>
    </div>

    <div class="section">
        <h2><i class="fas fa-envelope"></i> Contact Us</h2>
        <p>
            We'd love to hear from you! Whether you have questions, feedback, or partnership inquiries,
            our team is ready to assist.
        </p>

        <div class="contact-info">
            <div class="contact-method">
                <i class="fas fa-map-marker-alt"></i>
                <span>Kathmandu, Nepal</span>
            </div>

            <div class="contact-method">
                <i class="fas fa-phone"></i>
                <span>+977-9800000000</span>
            </div>

            <div class="contact-method">
                <i class="fas fa-envelope"></i>
                <span>contact@doctormerosaathi.com</span>
            </div>

            <div class="contact-method">
                <i class="fas fa-clock"></i>
                <span>Sun-Fri: 7AM - 7PM</span>
            </div>
        </div>
    </div>
</div>
</body>
</html>