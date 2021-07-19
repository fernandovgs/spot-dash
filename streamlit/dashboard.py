import streamlit as st
import requests

allAverages = requests.get('http://localhost:9000/v1/spot-dash/averages/all').json()['dailyAveragesDtos']

allEnergies = []
allValences = []

for avg in allAverages:
	allEnergies.append(avg['avgEnergy'])
	allValences.append(avg['avgValence'])

st.title('Spot-Dash')

st.write('Evolution of average energy from Spotify\'s trending 50 tracks')

st.line_chart(allEnergies)

st.write('Evolution of average valence from Spotify\'s trending 50 tracks')

st.line_chart(allValences)