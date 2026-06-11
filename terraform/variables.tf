variable "aws_region" {
  description = "AWS Region"
  type        = string
  default     = "ap-south-1"
}

variable "project_name" {
  description = "Project name - used in all resource names"
  type        = string
  default     = "java-devops"
}

variable "environment" {
  description = "Environment name"
  type        = string
  default     = "production"
}

# VPC
variable "vpc_cidr" {
  description = "VPC CIDR block"
  type        = string
  default     = "10.0.0.0/16"
}

variable "public_subnet_cidrs" {
  description = "Public subnet CIDRs (ALB ke liye)"
  type        = list(string)
  default     = ["10.0.1.0/24", "10.0.2.0/24"]
}

variable "private_subnet_cidrs" {
  description = "Private subnet CIDRs (EKS nodes ke liye)"
  type        = list(string)
  default     = ["10.0.3.0/24", "10.0.4.0/24"]
}

# EKS
variable "eks_cluster_version" {
  description = "Kubernetes version"
  type        = string
  default     = "1.29"
}

variable "node_instance_type" {
  description = "EC2 instance type for EKS nodes"
  type        = string
  default     = "t3.medium"
}

variable "node_min_size" {
  description = "Minimum EKS nodes"
  type        = number
  default     = 2
}

variable "node_max_size" {
  description = "Maximum EKS nodes"
  type        = number
  default     = 5
}

variable "node_desired_size" {
  description = "Desired EKS nodes"
  type        = number
  default     = 2
}

# RDS
variable "db_name" {
  description = "Database name"
  type        = string
  default     = "devopsdb"
}

variable "db_username" {
  description = "Database master username"
  type        = string
  default     = "admin"
}

variable "db_password" {
  description = "Database master password"
  type        = string
  sensitive   = true
}

variable "db_instance_class" {
  description = "RDS instance type"
  type        = string
  default     = "db.t3.micro"
}