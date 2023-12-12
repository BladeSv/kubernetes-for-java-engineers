{{- define "k8s.course.labels" }}
  labels:
    version: {{ .Chart.AppVersion }}
    date: {{ now | htmlDate }}
{{- end }}