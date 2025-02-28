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

variable "db_username" {
  description = "Username for the recipe app api database"
  default     = "root"
}

#variable "db_password" {
#  description = "Password for the Terraform database"
#}

variable "ecr_app_image" {
  description = "Path to the ECR repo with the API image"
  default     = "dummy-value"
}
