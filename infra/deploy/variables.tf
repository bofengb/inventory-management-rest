variable "prefix" {
  description = "Prefix for resources in AWS"
  default     = "ims"
}

variable "project" {
  description = "Project name for tagging resources"
  default     = "ims-app-api"
}

variable "contact" {
  description = "Contact email for tagging resources"
  default     = "ims@example.com"
}
