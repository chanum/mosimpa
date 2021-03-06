# MoSimPa

Monitoreo Simplificado de Pacientes en situación de internación masiva - MoSimPa (https://mosimpa.gitlab.io/)

Esta aplicación permite conectarse a un Broker MQTT y recibir los signos vitales de pacientes internados.

![](mosimpa-components.jpeg)

[Infobae Articulo](https://www.infobae.com/coronavirus/2020/05/14/el-ingenioso-desarrollo-argentino-para-evitar-que-medicos-y-enfermeros-se-contagien-de-coronavirus-y-supervisen-pacientes-a-distancia/)

# Architecture

MoSimPa Android App  was designed with clean architecture and MVVM implemented the S.O.L.I.D. principles.

## Layers
- Data
- Domain
- Presentation

## Libraries

- Flow
- LiveData
- Navigation Component
- Room
- Dagger2
- Coroutines
- Lifecycle
- GSON
- Paho MQTT Client
- MPAndroidChart

# Screen capture

![](mosimpa-capture.gif)



