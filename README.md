# 🤝 Orphanage Donation Platform

A web-based donation management platform that connects donors with verified orphanages through secure digital donations, financial transparency, and AI-powered donor assistance.

## 📌 Overview

The Orphanage Donation Platform is designed to simplify the process of donating to orphanages while improving transparency in fund utilization.

Donors can explore orphanage profiles, make secure online donations, and view graphical financial information about income and expenses. Orphanage administrators can manage their profiles, record expenses, maintain donation records, and provide bank and QR payment details.

The platform also includes a **Retrieval-Augmented Generation (RAG) chatbot** that answers donor queries using relevant information available within the system.

---

## ✨ Key Features

### 👤 Donor Module
- User registration and authentication
- Browse verified orphanage profiles
- View orphanage information
- Make secure online donations
- View donation history
- Access financial transparency reports
- Ask questions through the RAG-based chatbot

### 🏠 Orphanage Admin Module
- Orphanage registration and profile management
- Manage organization details
- Record and manage expenses
- Track donation records
- Add bank account details
- Upload QR code for digital payments

### 💰 Donation & Payment
- Secure online donation processing
- Payment gateway integration
- Transaction recording
- Donation history management

### 📊 Financial Transparency
- Income tracking through donation transactions
- Expense tracking by orphanage administrators
- Income and expense visualization
- Power BI dashboard integration
- Graphical representation of fund utilization

### 🤖 RAG-Based AI Assistant
- Donor-focused chatbot
- Answers questions using relevant platform data
- Retrieves information from the knowledge base
- Provides contextual responses to donor queries

### 🔐 Admin Management
- Verify orphanage registrations
- Manage users and orphanages
- Monitor donation transactions
- Maintain platform records

---

## 🏗️ System Architecture

```text
                         ┌───────────────┐
                         │     Donor     │
                         └───────┬───────┘
                                 │
                                 ▼
                    ┌────────────────────────┐
                    │    Web Application     │
                    │ Registration • Profile │
                    │ Donation • Dashboard   │
                    └───────────┬────────────┘
                                │
                                ▼
                    ┌────────────────────────┐
                    │     Backend Server     │
                    │ Authentication • Logic │
                    │ Donation • Expenses    │
                    └───────────┬────────────┘
                                │
              ┌─────────────────┼─────────────────┐
              ▼                 ▼                 ▼
       ┌─────────────┐  ┌───────────────┐  ┌──────────────┐
       │  Database   │  │    Payment    │  │ RAG Assistant│
       │             │  │    Gateway    │  │              │
       │ Users       │  │ Secure        │  │ Donor Query  │
       │ Donations   │  │ Transactions  │  │ Responses    │
       │ Expenses    │  └───────────────┘  └──────────────┘
       └──────┬──────┘
              │
              ▼
       ┌──────────────────────┐
       │ Orphanage Admin      │
       │ Profile • Expenses   │
       │ Donations • QR/Bank  │
       └──────────────────────┘
