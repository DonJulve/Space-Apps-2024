#!/bin/bash

# Variables
FRONTEND_DIR="./frontend"
BACKEND_DIR="./backend"
FRONTEND_PORT=4200
BACKEND_PORT=8080

echo "🚀 Starting local deployment for frontend and backend..."

# 1. Start the Backend (Spring Boot with Gradle)
echo "🔧 Starting backend (Spring Boot)..."
cd $BACKEND_DIR || exit
./gradlew bootRun >/dev/null 2>&1 &
BACKEND_PID=$!

# Wait a few seconds to allow the backend to start
echo "⏳ Waiting for the backend to start on port $BACKEND_PORT..."
sleep 10

# Check if the backend is running
if lsof -i:$BACKEND_PORT > /dev/null; then
    echo "✅ Backend started successfully on port $BACKEND_PORT."
else
    echo "❌ Failed to start the backend. Exiting..."
    kill $BACKEND_PID
    exit 1
fi

# 2. Start the Frontend (Angular)
echo "🔧 Starting frontend (Angular)..."
cd ..
cd $FRONTEND_DIR || exit
npm install >/dev/null 2>&1
ng serve >/dev/null 2>&1 & 
FRONTEND_PID=$!

# Verify if the frontend is running
echo "⏳ Waiting for the frontend to start on port $FRONTEND_PORT..."
sleep 10

if lsof -i:$FRONTEND_PORT > /dev/null; then
    echo "✅ Frontend started successfully on port $FRONTEND_PORT."
else
    echo "❌ Failed to start the frontend. Exiting..."
    kill $FRONTEND_PID
    kill $BACKEND_PID
    exit 1
fi

echo "🚀 Local deployment successful! Backend is running at http://localhost:$BACKEND_PORT and frontend at http://localhost:$FRONTEND_PORT."

# Wait for processes to complete
wait
