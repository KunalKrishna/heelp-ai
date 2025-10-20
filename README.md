# Heelp AI 🐏

*UNC Chapel Hill's Smart Answering Machine - Cutting Through Website Chaos*

[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.1+-6DB33F?logo=springboot)](https://spring.io/)
[![React](https://img.shields.io/badge/React-18+-61DAFB?logo=react)](https://reactjs.org/)
[![RAG](https://img.shields.io/badge/Architecture-RAG-FF6B6B)](https://arxiv.org/abs/2005.11401)
[![License](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)

## 🎯 The Problem

UNC Chapel Hill has **hundreds of subdomains** and websites, making it nearly impossible to:
- Find accurate information quickly
- Bookmark important pages
- Get answers during urgent situations
- Navigate bureaucratic processes efficiently

## ✨ The Solution

**Heelp AI** is a RAG (Retrieval-Augmented Generation) powered answering machine that:
- **Crawls** all relevant UNC websites continuously
- **Indexes** content in a smart vector database
- **Answers** your questions instantly with cited sources
- **Saves** you from the endless click-through nightmare

Ask natural questions like:
> "What's the deadline to drop a class this semester?"\
> "How do I get a parking permit for South Campus?"\
> "Where can I get free tutoring for organic chemistry?"

## 🏗️ Architecture

```mermaid
graph TB
    A[React Frontend] --> B[Spring Boot API]
    B --> C[Vector Database<br/>Chroma/Qdrant]
    B --> D[LLM<br/>Ollama/OpenAI]
    E[Web Crawler] --> F[Content Processor]
    F --> C
