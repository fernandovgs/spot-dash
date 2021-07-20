import streamlit as st
import requests as req
import pandas as pd

# Getting and processing necessary informations
allAverages = req.get('http://localhost:9000/v1/spot-dash/averages/all').json()['dailyAveragesDtos']
dailyTracks = req.get('http://localhost:9000/v1/spot-dash/track-infos').json()['trackInfosDtos']

allDays = []
allEnergies = []
allValences = []
dailyFeatures = []

for avg in allAverages:
	allDays.append(avg['analysisDate'])
	allEnergies.append(avg['avgEnergy'])
	allValences.append(avg['avgValence'])

for dailyTrack in dailyTracks:
	trackFeatures = [dailyTrack['energy'], dailyTrack['valence']]
	dailyFeatures.append(trackFeatures)

	del dailyTrack['id']
	del dailyTrack['receivedDate']


dailyChartData = pd.DataFrame(dailyTracks).rename(columns = {
	'name': 'Track',
	'artistName': 'Artist',
	'energy': 'Energy',
	'valence': 'Valence'
})
dailyFeaturesData = pd.DataFrame(dailyFeatures, columns = ['Energy', 'Valence'])
allEnergiesData = pd.DataFrame({
	'Date': allDays,
	'Energies': allEnergies
}).rename(columns={'Date': 'index'}).set_index('index')
allValencesData = pd.DataFrame({
	'Date': allDays,
	'Valences': allValences
}).rename(columns={'Date': 'index'}).set_index('index')

# Drawing dashboard

'''
# Spot-Dash


Analyzing averages of energy and valence to infer people's current mood,
according to Spotify's most listened songs

'''

'''
---

## Daily tracks


'''

dailyChartData


'''
---

## Energy and valence of each music


'''

st.line_chart(dailyFeaturesData)

'''
---

## Evolution of average energy from Spotify\'s trending 50 tracks


'''

st.line_chart(allEnergiesData)

'''
---

## Evolution of average valence from Spotify\'s trending 50 tracks


'''

st.line_chart(allValencesData)