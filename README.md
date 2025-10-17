Symptom Checker Backend
Overview

A Spring Boot backend that analyzes user symptoms and provides possible conditions, triage suggestions, and next steps. For educational purposes only.

Endpoints
POST /api/check

Request JSON:

{
  "symptoms": "fever, cough",
  "age": 25,
  "sex": "male",
  "duration": "2 days"
}


Response JSON:

{
  "conditions": [
    {
      "name": "Common Cold",
      "likelihood": "Moderate",
      "confidence": 0.6,
      "rationale": "Mild fever and sore throat indicate a common viral infection.",
      "suggestedNextSteps": ["Rest", "Hydration", "Consult doctor if >5 days"]
    }
  ],
  "triage": "routine",
  "disclaimer": "Educational only: This is not medical advice."
}

GET /api/health

Returns "OK" — checks server health.

Setup

Clone repository:

git clone <repo-url>
cd symptomchecker


Configure API key in application.properties:

server.port=7070
openai.api.key=YOUR_KEY
# or for Groq:
# groq.api.key=YOUR_KEY
# llm.api.url=https://api.groq.com/openai/v1/chat/completions


Run:

mvn spring-boot:run

Notes

Uses LLM APIs (OpenAI, Groq, or Hugging Face) or fallback mock data.

Outputs are informational only, not medical diagnosis.
